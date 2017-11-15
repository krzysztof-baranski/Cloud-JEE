#!/bin/bash

vagrant up 

vagrant ssh ignite-node1 -c "cp /vagrant/app/target/AppApplication-0.0.1-SNAPSHOT.jar /home/vagrant"
vagrant ssh ignite-node1 -c "nohup java -jar -Dserver.port=8081 /home/vagrant/AppApplication-0.0.1-SNAPSHOT.jar > AppApplication1-log.out 2>&1 & sleep 1"
vagrant ssh ignite-node1 -c "nohup java -jar -Dserver.port=8082 /home/vagrant/AppApplication-0.0.1-SNAPSHOT.jar > AppApplication2-log.out 2>&1 & sleep 1"