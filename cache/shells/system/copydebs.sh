#!/bin/bash

echo "------- shell copydebs -------"

apt-get install unzip -y
cd /home/vagrant
unzip archives.zip
dpkg -i /home/vagrant/archives/*.deb

rm -rf archives
rm -rf archives.zip