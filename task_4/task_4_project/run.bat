javac -d ./bin -encoding UTF-8 ./src/autoService/*.java
cd ./bin
jar cfev ../program.jar autoService.TestRun  ./autoService
cd ../
java -jar program.jar
