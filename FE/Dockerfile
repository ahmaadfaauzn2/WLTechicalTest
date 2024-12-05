# Menggunakan Node.js versi 22.11.0
FROM node:22.11.0

# Set direktori kerja dalam container
WORKDIR /app

# Menyalin package.json dan package-lock.json untuk menginstal dependencies terlebih dahulu
COPY package*.json ./

# Install dependencies
RUN npm install

# Menyalin seluruh kode aplikasi ke dalam container
COPY . .

# Build aplikasi React untuk production
RUN npm run build

# Install 'serve' secara global untuk menyajikan build aplikasi
RUN npm install -g serve

# Mengekspos port 3000 untuk aplikasi
EXPOSE 3000

# Perintah untuk menjalankan aplikasi menggunakan 'serve' setelah build
CMD ["serve", "-s", "build", "-l", "3000"]
