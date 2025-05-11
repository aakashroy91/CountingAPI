# CountingAPI

CountingAPI is a simple Spring Boot-based RESTful service that processes a list of strings and provides endpoints to analyze word patterns using Spring Boot 3.4.5 and Java 17

## Features

- Count words that start with a given character.
- Retrieve words longer than a specified length.
- Dockerized deployment.
- API documentation via Swagger.
- Test coverage and security dependency reports.

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6.3 or later
- Docker 17.09 or higher

### Install Java (JDK 17+)
- Ubuntu/Debian:
```bash
sudo apt update
sudo apt install openjdk-17-jdk
java -version
```
- macOS (using Homebrew):
```bash
brew install openjdk@17
brew link --force --overwrite openjdk@17
java -version
```
- Windows:
Download from the official Oracle JDK or OpenJDK.
Install and set JAVA_HOME in Environment Variables.
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

###Install Maven (3.6.3+)
- Ubuntu/Debian:
```bash
sudo apt install maven
mvn -version
```
- macOS (using Homebrew):
```bash
brew install maven
mvn -version
```
- Windows:
Download from the official Maven website.
Extract and add bin directory to your PATH.
https://maven.apache.org/download.cgi

### Install Docker
- Ubuntu/Debian:
```bash
sudo apt update
sudo apt install docker.io
sudo systemctl start docker
sudo systemctl enable docker
docker --version
```
- macOS:
Install Docker Desktop from
```bash
https://www.docker.com/products/docker-desktop
```
- Windows:
Install Docker Desktop from below link
Enable WSL2 and virtualization if prompted.
https://www.docker.com/products/docker-desktop

### Clone the Repository

```bash
git clone https://github.com/aakashroy91/CountingAPI.git
cd CountingAPI/
git checkout main
```
### Build the Project

```bash
mvn clean verify -Ddependency-check.skip=true
mvn clean package
```
### Docker Setup

```bash
docker build -t countingapi:0.0.1 .
docker tag countingapi:0.0.1 countingapi:latest
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev countingapi
```
- Your app is now running on port 8080
- Check API status using below Endpoint. Output : "Api working and Up"
```bash
http://localhost:8080/api/v1/counting/test
```

### API Usage
- sample INPUT JSON DATA :
```bash
{
  "wordData": ["apple", "banana my name is this banana", "cherry bunny", "Bamboo"]
}
```

### REST Endpoints 
Content-Type : application/json
```bash
POST http://localhost:8080/api/v1/counting/matched-count/{char}
```
```bash
POST http://localhost:8080/api/v1/counting/matched-words/{length}
```

### Reports
- Coverage Report: ~/CountingAPI/target/site/jacoco/index.html
- Dependency Check Report: ~/CountingAPI/target/dependency-check-report.html

### API Documentation
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml

### Minimum Code Coverage
-Currently code coverage percentage is set to 80%. Jar will not be created if this condition is not met. To modify this change rule setting in jacoco plugin information in pom.xml file
```bash
<rule>
<element>BUNDLE</element>
<limits>
<limit>
<counter>INSTRUCTION</counter>
<value>COVEREDRATIO</value>
<minimum>0.80</minimum> //change this value to reduce coverage check.
</limit>
</limits>
</rule>
```
