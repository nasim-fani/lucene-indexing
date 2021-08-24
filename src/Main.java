package src;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.util.*;
<<<<<<< HEAD

public class Main {
    static String indexDir = "D:/dars/term7/IR/python/git/information_retrieval/exercise2/src/indexDir";
    static String dataDir = "D:/dars/term7/IR/python/git/information_retrieval/exercise2/src/group3_103.txt";
    //    Indexer indexer;
    static Set<String> hash_Set = new HashSet<String>();
=======


public class Main {

    static String indexDir = "";
    static String dataDir = "";
    static Set<String> hash_Set = new HashSet<String>();

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
    public static void main(String[] args){
        String content = null;
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
<<<<<<< HEAD
            System.out.print("please inter index directory path:: ");
            indexDir = reader.readLine();
            System.out.print("please inter text file path for indexing:: ");
            dataDir = reader.readLine();
//
=======
            System.out.print("please Enter index directory path:: ");
            indexDir = reader.readLine();
            System.out.print("please Enter text file path for indexing:: ");
            dataDir = reader.readLine();
            content = readFile(dataDir, StandardCharsets.UTF_8);

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
            Analyzer analyzer = CustomAnalyzer.builder()
                    .withTokenizer("standard")
                    .addTokenFilter("lowercase")
                    .addTokenFilter("stop")
                    .addTokenFilter("porterstem")

                    .build();
<<<<<<< HEAD
            content = readFile(dataDir, StandardCharsets.UTF_8);
=======

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
            ArrayList<Document> docs = getDocuments(content,analyzer);

            Analyzer analayzer2 = new WhitespaceAnalyzer();
            analyze(docs,analayzer2);
            Searcher s = new Searcher(indexDir,analayzer2,hash_Set);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
=======

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
    public static void analyze(ArrayList<Document> documents, Analyzer analyzer){
        try{
            Directory dir = FSDirectory.open(Paths.get(indexDir));

            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter writer = new IndexWriter(dir, iwc);
            for(int i=0 ; i<documents.size() ; i++){
                writer.addDocument(documents.get(i));
                if(i==0) iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }
            writer.close();
        }catch(IOException e){
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
    }
<<<<<<< HEAD
=======

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
    public static ArrayList<Document> getDocuments(String content,Analyzer analyzer){
        Scanner scan = new Scanner(content);
        ArrayList<Document> docs = new ArrayList<>();
        int i=0;
        String str = "";
        while (scan.hasNextLine()){
            String readLine = scan.nextLine();
            if(readLine.contains(".I "+(i+207))){
                if(i!=0) {
                    String stringAnalyzed = getAnalyzedString(analyzer,str);
                    Document doc = new Document();
                    doc.add(new TextField("content",stringAnalyzed,Field.Store.YES));
                    docs.add(doc);
                }
                str = "";
                i++;
            }else if(!readLine.contains("I "+(i+207)) && !readLine.contains(".W")){
                str+=" "+readLine;
            }
        }
        //last paragraph remain so it must be documented
        Document doc = new Document();
        String stringAnalyzed = getAnalyzedString(analyzer,str);
        doc.add(new TextField("content",stringAnalyzed,Field.Store.YES));
        docs.add(doc);
        return docs;
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
<<<<<<< HEAD
    public static String getAnalyzedString(Analyzer analyzer,String content){
        TokenStream tokenStream = analyzer.tokenStream("content",content);
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

=======

    public static String getAnalyzedString(Analyzer analyzer,String content){
        TokenStream tokenStream = analyzer.tokenStream("content",content);
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
        String stringAnalyzed = "";
        try{
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
<<<<<<< HEAD
//                int startOffset = offsetAttribute.startOffset();
//                int endOffset = offsetAttribute.endOffset();
=======
                int startOffset = offsetAttribute.startOffset();
                int endOffset = offsetAttribute.endOffset();
>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
                String term = charTermAttribute.toString();
                hash_Set.add(term);
//                            System.out.println(term);
                stringAnalyzed+=term+" ";
            }
            tokenStream.close();
        }catch (IOException e){
            System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
        }
        return stringAnalyzed;
    }
<<<<<<< HEAD
=======

>>>>>>> 77e167fccc08c332e0a8578082723bfef6b42788
}





