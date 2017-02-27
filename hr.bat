set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_111
call d:\mvn\bin\mvn -B -s settings.xml -DskipTests=true clean package
call java -Dspring.profiles.active="datajpa,heroku" -DDATABASE_URL="postgres://user:password@localhost:5432/topjava" -jar target/dependency/webapp-runner.jar target/*.war