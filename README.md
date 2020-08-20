# spring-boot-rabbitmq-demo
Demo of Rabbit-mq using spring-boot

Run the rabbit-mq as a docker conatiner
> docker run -d -p 8090:5672 -p 8095:15672 --hostname my-rabbit --name my-rabbit rabbitmq:3-management

This means the rabbit-mq server is running on port 5672 and will listen on localhost port 8090 and 
rabbit-mq admin console is running on port 15672 and will listen on localhost port 8095
