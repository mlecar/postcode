### Author
	Name: Marcelo Lecar de Carvalho
	email: marcelo.lecar@gmail.com

## Environment
	Maven: 
		Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T14:41:47-02:00)
	
	Java: 
		java version "1.8.0_92"
		Java(TM) SE Runtime Environment (build 1.8.0_92-b14)
		Java HotSpot(TM) 64-Bit Server VM (build 25.92-b14, mixed mode)
		
	IDE:
		Spring Tool Suite 3.6.4.RELEASE

## How to build

    $ mvn clean install

## How to execute

	Using 3rd party api
		$ java -jar postcode-service-1.0.0-SNAPSHOT.jar
		
	Using mocked responses
		$ java -Dmocked.postcoder.api.url="localhost:8080/mocked" -jar target/postcode-service-1.0.0-SNAPSHOT.jar
	
## Docker
	src/main/docker
		docker-compose.yml
		Dockerfile
		postcode-service-1.0.0-SNAPSHOT.jar
    
## How to test/use

	curl:
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/ie/D02X285?lines=3&format=json"
		$ curl -v -X GET "http://localhost:8080/http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/ie/D02X285?lines=3&format=xml"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/addressgeo/ie/Adelaide%20Road?format=json"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/addressgeo/ie/Adelaide%20Road?format=xml&lines=3"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/addressgeo/ie/D02X285?format=json&addtags=w3w"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/position/ie/D02X285?format=json"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/position/ie/D02X285?format=xml"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/rgeo/ie/53.332067/-6.255492?distance=50&format=json"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/rgeo/ie/53.332067/-6.255492?distance=50&format=xml&lines=3"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/uk/NR147PZ?format=json"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/uk/NR147PZ?format=xml"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/uk/manor%20farm%20barns?format=xml"
		$ curl -v -X GET "http://localhost:8080/pcw/PCW45-12345-12345-1234X/address/uk/NR147PZ?format=json&lines=3"
	
		