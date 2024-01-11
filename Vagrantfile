Vagrant.configure("2") do |config|
  # Ange bas-VM
  config.vm.box = "ubuntu/bionic64"

  # Synkronisera nuvarande mapp med /vagrant på gäst-VM
  config.vm.synced_folder ".", "/vagrant"

  # Portvidarebefordran från värdmaskin till gäst-VM
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  # Skript för att installera Java och Spring Boot
  config.vm.provision "shell", inline: <<-SHELL
    # Uppdatera paketkällor
    sudo apt-get update

    # Installera Java (OpenJDK)
    sudo apt-get install -y openjdk-11-jdk

    # Sätt Java Home (lägg till i .bashrc för automatisk konfiguration)
    echo "export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64" >> /home/vagrant/.bashrc
    echo "export PATH=\$PATH:\$JAVA_HOME/bin" >> /home/vagrant/.bashrc

    # Installera Spring Boot CLI
    sudo add-apt-repository ppa:jonathonf/texlive
    sudo apt-get update
    sudo apt-get install -y spring-tool-suite

    # Installera Maven för bygghantering (valfritt)
    sudo apt-get install -y maven
  SHELL

  # Skript för att installera MySQL
  config.vm.provision "shell", inline: <<-SHELL
    # Installera MySQL
    sudo apt-get install -y mysql-server

    # Säkerhetskonfigurationer (kan anpassas)
    sudo mysql_secure_installation

    # Skapa en databas och användare (ersätt med dina värden)
    sudo mysql -e "CREATE DATABASE my_database;"
    sudo mysql -e "CREATE USER 'my_user'@'localhost' IDENTIFIED BY 'my_password';"
    sudo mysql -e "GRANT ALL ON my_database.* TO 'my_user'@'localhost';"
    sudo mysql -e "FLUSH PRIVILEGES;"

    # Ladda in data till databasen (ersätt med ditt skript/fil)
    # sudo mysql -u my_user -pmy_password my_database < /vagrant/my_data.sql
  SHELL
end
