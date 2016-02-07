#! /usr/bin/env python

import random

data = []
for x in range(100):
    data.append(random.randint(-5, 5))

with open('input.txt', 'w') as output:
    for val in data:
        print(val, file=output)

with open('input.csv', 'w') as output:
    print(data, sep=',', file=output)
