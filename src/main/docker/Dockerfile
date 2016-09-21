FROM centos:7

RUN yum update

#RUN yum install -y unzip
#RUN yum install -y curl
RUN yum install -y libstdc++.i686
RUN yum install -y less
RUN yum install -y prelink

RUN curl -v -j -k -O -L -H "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u51-b16/jdk-8u51-linux-i586.rpm > jdk-8u51-linux-i586.rpm
RUN yum install -y jdk-8u51-linux-i586.rpm

RUN mkdir /app
RUN mkdir /app/postcode

COPY *.jar /app/postcode

WORKDIR app/postcode

EXPOSE 8080

CMD java -jar postcode-service-1.0.0-SNAPSHOT.jar
