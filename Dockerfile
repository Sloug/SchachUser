FROM phusion/baseimage
RUN apt-get update && apt-get -o Dpkg::Options::='--force-confold' --force-yes -fuy dist-upgrade
RUN apt-get install git default-jdk wget -y
RUN wget www.scala-lang.org/files/archive/scala-2.13.2.deb
RUN dpkg -i scala-2.13.2.deb
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN apt-get update
RUN apt-get install sbt -y --allow-unauthenticated
RUN git clone https://github.com/Sloug/SchachUser.git
RUN cd /SchachUser && sbt compile
ENTRYPOINT cd /SchachUser && sbt run







#RUN useradd -m -p jY0VSieLnllmM -s /bin/bash webuser
#RUN apt-get update && apt-get -o Dpkg::Options::='--force-confold' --force-yes -fuy dist-upgrade
#RUN apt-get install python2.7 python man manpages manpages-dev clang gdb valgrind make build-essential fonts-powerline git tmux nano zsh wget curl sudo net-tools -y
#RUN sh -c "$(wget -O- https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
#RUN chsh -s /bin/zsh
#RUN cd /root && wget https://github.com/sharkdp/bat/releases/download/v0.15.1/bat_0.15.1_amd64.deb
#RUN dpkg -i /root/bat_0.15.1_amd64.deb
#RUN rm /root/bat_0.15.1_amd64.deb
#RUN adduser webuser sudo
#RUN cd /root && wget https://storage.googleapis.com/golang/go1.9.2.linux-amd64.tar.gz
#RUN tar -zxvf /root/go1.9.2.linux-amd64.tar.gz -C /usr/local/
#RUN rm /root/go1.9.2.linux-amd64.tar.gz
#RUN export PATH=$PATH:/usr/local/go/bin
#RUN /usr/local/go/bin/go get github.com/yudai/gotty
#RUN echo "# resize not smaller than my client" >> /home/webuser/tmux.conf
#RUN echo "set-window-option -g aggressive-resize on" >> /home/webuser/tmux.conf
#RUN echo "# Created by newuser for 5.1.1" >> /home/webuser/.zshrc
#RUN cp /root/go/bin/gotty /home/webuser/gotty
#RUN chown -R webuser:webuser /home/webuser
#RUN rm /etc/ssh/sshd_config
#RUN echo "Port 22" >> /etc/ssh/sshd_config
#RUN echo "LoginGraceTime 120" >> /etc/ssh/sshd_config
#RUN echo "PermitRootLogin no " >> /etc/ssh/sshd_config
#RUN echo "StrictModes yes" >> /etc/ssh/sshd_config
#RUN echo "PermitEmptyPasswords no" >> /etc/ssh/sshd_config
#RUN echo "Protocol 2" >> /etc/ssh/sshd_config
#RUN echo "UsePrivilegeSeparation yes" >> /etc/ssh/sshd_config
#RUN echo "ClientAliveInterval 120" >> /etc/ssh/sshd_config
#RUN echo "ClientAliveCountMax 720" >> /etc/ssh/sshd_config
#RUN echo "sshd: ALL" >> /etc/hosts.allow
#ENTRYPOINT service ssh restart && /bin/zsh



#user webuser pw webshell123
#run with sudo docker run -itd -p 8080:8080 -p 8022:22 rafthedocker/getty
#then log in with ssh: ssh -p 8022 webuser@localhost
#then run /home/webuser/gotty --width 1024 --height 768 tmux new -A -s gotty zsh &
#then call localhost:8080 in your webbrowser (otherwise it could happen that tmux wont accept a connection??)
#then run tmux -> ctrl + b -> s -> choose session

