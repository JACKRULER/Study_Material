FROM java:8

MAINTAINER Jeroen Knoops <jeroen.knoops@gmail.com>

#=========
# Env variables
#=========

ENV GROOVY_VERSION 2.4.5
ENV CHROME_DRIVER_VERSION 2.20


#=========
# Adding Headless Selenium with Chrome and Firefox
#=========

# Chrome
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
RUN apt-get update && apt-get install -y \
    google-chrome-stable

# Dependencies to make "headless" selenium work
RUN apt-get -y install \
    gtk2-engines-pixbuf \
    libxtst6 \
    xfonts-100dpi \
    xfonts-75dpi \
    xfonts-base \
    xfonts-cyrillic \
    xfonts-scalable \
    xvfb

# Starting xfvb as a service
ENV DISPLAY=:99
ADD xvfb /etc/init.d/
RUN chmod 755 /etc/init.d/xvfb

RUN mkdir /app

RUN useradd  -s /bin/bash -d /app/ app && \
    chown app:app /app

USER app

COPY entrypoint.sh /app

WORKDIR /app

ENTRYPOINT ["./entrypoint.sh"]
