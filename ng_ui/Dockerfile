FROM nginx:1.17.1-alpine
#COPY --from=build-deps /usr/src/app/build /usr/share/nginx/html
COPY default.conf /etc/nginx/conf.d/default.conf
COPY dist /usr/share/nginx/html
#COPY dist /app
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
