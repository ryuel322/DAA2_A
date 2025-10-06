[README.md](https://github.com/user-attachments/files/22713040/README.md)
# Shell Sort based on src directory
# all code/classes required and well writed 



# Shell Sort Benchmark Analysis

## Algorithm
Shell Sort with Ciura gap sequence


## Benchmark Results
- Input sizes: 100, 1000, 10000, 100000
- Distributions: random, sorted, reversed, nearly-sorted
- Metrics: time, comparisons, swaps

## How to Run
```bash
java -cp target/classes cli.BenchmarkRunner algorithm=shell size=10000 dist=random runs=5

