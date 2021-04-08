#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/diplom-1.0-SNAPSHOT.jar \
    root@116.203.114.62:/root/

echo 'Restart server...'

ssh  -tt -i ~/.ssh/id_rsa root@116.203.114.62 << EOF
sudo pgrep java | xargs kill -9
sudo nohup java -jar diplom-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'