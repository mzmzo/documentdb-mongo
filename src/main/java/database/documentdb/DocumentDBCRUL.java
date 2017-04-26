/**    
 * <p>Copyright (c) Shanghai TY Technology Co., Ltd. All Rights Reserved.</p>
 *
 * @FileName: 	MongoDB_DocumentDB.java    
 * @Description:MongoDB_DocumentDB  
 * @author: 	Administrator
 * @Creat: 		2017年4月21日  
 *
 * Modification History:
 * Data			Author		Version		   Description
 * -------------------------------------------------------------
 * 2017年4月21日		Administrator		
 */
package database.documentdb;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.FeedResponse;
import com.microsoft.azure.documentdb.RequestOptions;

import database.Application;

/**  
 * @ClassName: MongoDB_DocumentDB        
 */
@RunWith(SpringRunner.class)
//指定SpringBoot工程的Application启动类
//支持web项目
@WebAppConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DocumentDBCRUL {

    /**
     * 通过documentDB API操作
     */
    public static void main(String[] args) throws Exception {
        DocumentClient client = DocumentClientFactory.getDocumentClient();
        //创建数据库
        Database database = new Database();
        database.setId("TagDTO");
        client.createDatabase(database, null);

        //添加集合
        DocumentCollection collectionInfo = new DocumentCollection();
        collectionInfo.setId("tag-coll1");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setOfferThroughput(400);
        client.createCollection("/dbs/TagDTO", collectionInfo, requestOptions);

        //创建 JSON 文档
        Family andersenFamily = new Family();
        andersenFamily.setId("Andersen.1");
        andersenFamily.setLastName("Andersen");
        Parent parent1 = new Parent("jack");
        Parent parent2 = new Parent("pony");
        andersenFamily.setParents(new Parent[] { parent1, parent2 });
        andersenFamily.setDistrict("WA5");
        Address address = new Address();
        address.setCity("Seattle");
        address.setCounty("King");
        address.setState("WA");
        andersenFamily.setAddress(address);
        andersenFamily.setRegistered(true);
        client.createDocument("/dbs/TagDTO/colls/tag-coll1", andersenFamily, new RequestOptions(), true);

        //查询 DocumentDB 资源
        FeedResponse<Document> queryResults = client.queryDocuments("/dbs/TagDTO/colls/tag-coll1",
                "SELECT * FROM Family WHERE Family.lastName = 'Andersen'", null);

        System.out.println("Running SQL query...");
        for (Document family : queryResults.getQueryIterable()) {
            System.out.println(String.format("\tRead %s", family));
        }

        //替换 JSON 文档
        Child Child1 = new Child();
        Child1.setFamilyName("first");
        Child[] Childs = new Child[] { Child1 };
        andersenFamily.setChildren(Childs);
        client.replaceDocument("/dbs/TagDTO/colls/tag-coll1/docs/Andersen.1", andersenFamily, null);

        // 删除 JSON 文档
        client.deleteDocument("/dbs/TagDTO/colls/tag-coll1/docs/Andersen.1", null);

        //删除数据库 
        client.deleteDatabase("/dbs/TagDTO", null);
        System.out.println("OK");
    }
}
