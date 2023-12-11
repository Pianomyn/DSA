def binary_search(arr, search_term):
    """
    Iterative Approach:
    Time: O(logn)
    Space: O(1)

    Recursive Approach:
    Time: O(logn)
    Space: O(logn) (Each iteration places function call on call stack)
    """
    low = 0
    mid = 0
    high = len(arr) - 1

    while low <= high:
        mid = (low + high) // 2

        if arr[mid] < search_term:
            low = mid + 1
        elif arr[mid] > search_term:
            high = mid - 1
        else:
            return mid

    return -1


if __name__ == "__main__":
    arr = input("Please enter numbers separated by commas. Eg 4,91,2: ")
    arr = sorted([int(x) for x in arr.split(",")])
    print(arr)

    search_term = int(input("Please enter a number to search for: "))

    print(binary_search(arr, search_term))
