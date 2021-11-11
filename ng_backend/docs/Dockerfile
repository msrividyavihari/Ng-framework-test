FROM nginx:alpine

## Replace the default nginx index page with our Angular app
RUN rm -rf /usr/share/nginx/html/*
COPY build/docs/asciidoc /usr/share/nginx/html

ENTRYPOINT ["nginx", "-g", "daemon off;"]