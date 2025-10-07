# Cria a chave privada (usada para assinar)
openssl genrsa -out src/main/resources/private.pem 2048

# Cria a chave pública (usada para verificar)
openssl rsa -in src/main/resources/private.pem -pubout -out src/main/resources/public.pem
