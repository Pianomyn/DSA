values = [60, 50, 70, 30]
weights = [5, 3, 4, 2]
cap = 5

dp = []
for _ in range(len(values)):
    to_add = [0] * (cap + 1)
    dp.append(to_add)

for v in range(len(values)):
    for w in range(cap + 1):
        # First row
        if v == 0:
            if weights[v] <= w:
                dp[v][w] = values[v]
            continue

        # Weight of item > current cap
        if weights[v] > w:
            dp[v][w] = dp[v-1][w]
            continue

        take = values[v] + dp[v - 1][w - weights[v]]
        leave = dp[v - 1][w]
        dp[v][w] = max(take, leave)


for e in dp:
    print(e)
print(dp[len(values) - 1][cap])
