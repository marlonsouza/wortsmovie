# WorstMovie

## Como executar

### 1. Clone o repositório
```bash
git clone https://github.com/marlonsouza/wortsmovie.git
cd worstmovie
```

### 2. Execute o projeto
```bash
mvn clean install
mvn spring-boot:run
```

### 3. Para executar os tests
```bash
mvn test
```

## API Endpoints

### GET /interval-awards
Retorna os produtores com maiores e menores intervalos entre vitórias consecutivas.

**Parâmetros:**
- `limit` (opcional): Número máximo de resultados (padrão: 2)

**Exemplo de requisição:**
```bash
curl "http://localhost:8080/interval-awards?limit=3"
```

**Exemplo de resposta:**
```json
{
  "min": [
    {
      "producer": "Marlon Brando",
      "interval": 1,
      "previousWin": 1990,
      "followingWin": 1991
    }
  ],
  "max": [
    {
      "producer": "Tom hanks",
      "interval": 13,
      "previousWin": 2002,
      "followingWin": 2015
    }
  ]
}
```