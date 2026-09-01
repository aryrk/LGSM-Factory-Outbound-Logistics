FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
COPY .env.example .env.example

EXPOSE 5173

CMD ["sh", "-c", "if [ ! -f .env ]; then cp .env.example .env; fi && npm run dev -- --host 0.0.0.0 --port 5173"]
