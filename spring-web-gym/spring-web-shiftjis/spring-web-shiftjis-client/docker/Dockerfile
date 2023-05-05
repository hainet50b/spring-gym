FROM httpd:2.4.55

RUN mkdir htdocs/shift-jis
COPY Shift_JIS.txt htdocs/
COPY Shift_JIS.txt htdocs/shift-jis

RUN mkdir htdocs/utf-8
COPY UTF-8.txt htdocs/
COPY UTF-8.txt htdocs/utf-8

COPY charset.conf ./
RUN cat charset.conf >> conf/httpd.conf

CMD ["httpd-foreground"]
