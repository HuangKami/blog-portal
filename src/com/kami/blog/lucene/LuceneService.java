package com.kami.blog.lucene;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kami.blog.model.Article;
import com.kami.blog.service.ArticleService;
import com.kami.blog.util.StringHelper;

import net.paoding.analysis.analyzer.PaodingAnalyzer;



@Service
public class LuceneService {
	public static final String PATH = "kami";
	public static final int SIZE = 1000 ;
	private Logger logger = Logger.getLogger(LuceneService.class);
	@Autowired
	public ArticleService articleService;
	
	public void createIndex(Article article) {
		IndexWriter writer = null;
		try {
			Directory directory = FSDirectory.open(new File(PATH));
			Analyzer analyzer = new PaodingAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			writer = new IndexWriter(directory, config);
			Document document = createDocument(article);
			writer.addDocument(document);
			writer.commit();
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				logger.error(e, e);
			}
		}
	}
	
	
	public void deleteIndex(int id) {
		IndexWriter writer = null;
		try {
			Directory directory = FSDirectory.open(new File(PATH));
			Analyzer analyzer = new PaodingAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
			writer = new IndexWriter(directory, config);
			writer.deleteDocuments(new Term("id", StringHelper.integerToString(id)));
			writer.commit();
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				logger.error(e, e);
			}
		}
	}
	
	public List<Article> search(String keyword) {
		List<Article> articles = new LinkedList<>();
		DirectoryReader reader = null;
		try {
			Directory directory = FSDirectory.open(new File(PATH));
			reader = DirectoryReader.open(directory);
			Analyzer analyzer = new PaodingAnalyzer();
			IndexSearcher searcher = new IndexSearcher(reader);
			String[] fields = {"topic", "title", "content"}; 
			BooleanClause.Occur[] clauses = {BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
			Query multiFieldQuery = MultiFieldQueryParser.parse(Version.LUCENE_48, keyword, fields, clauses, analyzer);
			TopDocs topDocs = searcher.search(multiFieldQuery, SIZE);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
	        Article article = null;
	        for (ScoreDoc scoreDoc : scoreDocs) {
				Document document = searcher.doc(scoreDoc.doc);
				article = new Article();
				article.setId(StringHelper.stringToInteger(document.get("id")));
				article.setTitle(document.get("title"));
				String content = document.get("content");
				article.setContent(content.substring(0, Math.min(300, content.length())));
				article.setTopic(document.get("topic"));
				articles.add(article);
			}
		} catch (Exception e) {
			logger.error(e, e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e, e);
				}
			}
		}
		
		return articles;
	}
	
	private Document createDocument(Article article) {
		Document document = new Document();
		document.add(new IntField("id", article.getId(), Field.Store.YES));
		document.add(new TextField("title", article.getTitle(), Field.Store.YES));
		document.add(new TextField("content", articleService.format(article.getContent()), Field.Store.YES));
		document.add(new TextField("topic", article.getTopic(), Field.Store.YES));
		return document;
	}
}
