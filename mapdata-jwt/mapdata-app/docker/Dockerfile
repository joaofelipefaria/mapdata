# Etapa 1: Construção da aplicação Angular
FROM node:18 AS build

WORKDIR /app

# Copiar package.json e instalar dependências
COPY package.json package-lock.json ./
RUN npm install

# Copiar todo o código-fonte
COPY . .
RUN npm run build --prod

# Etapa 2: Servindo a aplicação com Nginx
FROM nginx:alpine

# Copiar arquivos do build Angular para a pasta do Nginx
COPY --from=build /app/dist/mapdata-app /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
