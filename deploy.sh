sudo /opt/apache-tomcat-8.0.24/bin/shutdown.sh
sudo cp /home/krupt/git/interview-rti/target/rti.war /opt/apache-tomcat-8.0.24/webapps
sudo rm -R /opt/apache-tomcat-8.0.24/webapps/rti
cd /opt/apache-tomcat-8.0.24
sudo /opt/apache-tomcat-8.0.24/bin/startup.sh
cd /home/krupt/git/interview-rti
