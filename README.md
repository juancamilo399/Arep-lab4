# Arep-lab4

In this workshop a Web server (Apache type) was built in Java. The server is capable of delivering html pages. The server also provides an IoC framework for building web applications from POJOS.


![Heroku](https://pyheroku-badge.herokuapp.com/?app=warm-sands-05374&style=flat)
[![CircleCI](https://circleci.com/gh/juancamilo399/Arep-lab4.svg?style=svg)](https://app.circleci.com/pipelines/github/juancamilo399/Arep-lab4)

[Deploy to heroku](https://warm-sands-05374.herokuapp.com/index.html)

To test [microSpring](https://warm-sands-05374.herokuapp.com/Apps/hola)

## Getting Started

The following instructions will allow you to have a copy of the project and run it on your machine.

### Prerequisites

* [Maven](https://maven.apache.org/) - Dependency Management

* [Java 8](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html) -  Development Environment 

* [Git](https://git-scm.com/) - Version Control System

### Installing

1. Clone the repository

```
git clone https://github.com/juancamilo399/Arep-lab4.git
```

2. Compile the projet

```
mvn package
```

3. Executing the program

```
mvn exec:java -D "exec.mainClass"="co.edu.escuelaing.sparkd.SparkD.SparkDServer"

In your browser localhost:36000/index.html
```
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Author

* **Juan Camilo Angel Hernandez** 


## License

This project is under GNU General Public License - see the [LICENSE](LICENSE) file for details
