# Spring Boot搭建RestFul Api Server

## 準備作業
此範本需要`MySQL`資料庫及`JDK17`環境  
支援`lombok plugin`

#### Create Table(default schema: master)
可選擇以下兩種方式，擇一使用。
1. 使用`docker-compose.yml`建立簡易環境。
2. 安裝`MySQL`資料庫，並使用`product_schema.sql`建立Table

## 啟動方式
基本執行指令:
- `mvn spring-boot:run`

後面可帶參數決定不同環境變數執行，例如
- `mvn spring-boot:run -Ptest`

會根據`pom.xml`的`<profiles>`內容引入相對應變數

## 測試案例
查詢(Get)
><http://localhost:8080/product/{id}>

新增(Post)
><http://localhost:8080/product>  
>body: `{ "enName": "enName", "zhName": "zhName", "price": 50 }`

完整更新(Put)
><http://localhost:8080/product/{id}>  
>body: `{ "enName": "enNamePut", "zhName": "zhNamePut", "price": 100 }`

部分更新(Patch)
><http://localhost:8080/product/{id}>  
>body: `{ "enName": "enNamePath" }`

刪除(Delete)
><http://localhost:8080/product/{id}>  

## 使用Swagger產生規則文件
Swagger Api文件
><http://localhost:8080/v3/api-docs>

開啟Swagger editor網站
><https://editor.swagger.io/>

將產出的內容放入，即可產生API規格文件。

## 創建Table的Entity
##### JPA
在`entityGenConfig.yml`內部添加table設定及生成參數，添加的細節在註解有說明。  
執行maven generator: 
- `mvn jpa-entity-generator:generateAll`

加上-e可查看generator過程:
- `mvn jpa-entity-generator:generateAll -e`

執行後根據`jpaEntityGenerator.yaml`配置的參數產生至對應的位置。  

##### QueryDSL
將`pom.xml`的`apt-maven-plugin`開啟。  
執行compiler: 
- `mvn clean compiler`

執行後根據`<outputDirectory>`配置產生至對應的位置，手動移動至package。  
產生完之後需要將plugin註解起來，以防後續compiler錯誤。  

並於Dao繼承QuerydslPredicateExecutor。