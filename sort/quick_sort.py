def quick_sort(l: list) -> list:
    if not l:
        return []

    smaller = []
    same = []
    larger = []

    pivot = l.pop()
    while l:
        current = l.pop()
        if current < pivot:
            smaller.append(current)
        elif current == pivot:
            same.append(current)
        else:
            larger.append(current)
    same.append(pivot)

    smaller = quick_sort(smaller)
    larger = quick_sort(larger)

    return smaller + same + larger


if __name__ == "__main__":
    string_input = input("Enter integers separated by commas. Eg 1,10,3,2\n")
    l = list(map(int, string_input.split(",")))
    print(quick_sort(l))
