#!/bin/bash

echo "------- shell ignite -------"

unzip -q /home/vagrant/ignite.zip -d .
mv /home/vagrant/apache-ignite-fabric-1.9.0-bin /usr/local/ignite
chown vagrant:vagrant -R /usr/local/ignite