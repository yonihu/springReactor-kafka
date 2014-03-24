springReactor-kafka
===================
* download & install Kafka http://kafka.apache.org/downloads.html
  ** windows: select version 0.8 - [http://janschulte.wordpress.com/2013/10/13/apache-kafka-0-8-on-windows/]
  ** windows utility commands:
  start zookeeper
  ***    bin/zookeeper-server-start.sh config/zookeeper.properties - run zookeepr
  start kafka
  ***    bin/kafka-server-start.sh config/server.properties - run kafka

  create kafka topic
  ***   bin/kafka-create-topic.sh --zookeeper localhost:2181 --replica 1 --partition 1 --topic test  - create topic "test"
  check topic was created
  ***   bin/kafka-list-topic.sh --zookeeper localhost:2181 - return list of topics (check if test was created)
  
  tail on consume topic
  ***   bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test  --from-beginning   --  recive a messages from "test" topic
  *** 

* download & install redis http://redis.io/download
  **    windows:   http://cloud.github.com/downloads/rgl/redis/redis-2.2.12-setup-32-bit.exe
