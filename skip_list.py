from random import randint

MAX_LEVEL = 10

class Node:
    def __init__(self, key, value, level) -> None:
        self.key = key
        self.value = value
        self.level = level
        self.forwards = []
        for i in range(level):
            self.forwards.append(None)

    def incrLevel(self, level):
        assert(level > self.level)
        for i in range(self.level, level):
            self.forwards.append(None)
        self.level = level


class SkipList:
    def __init__(self) -> None:
        self.header = Node(0, 0, 1)  # sentinel node

    def insert(self, key, value) -> None:
        header = self.header
        node = header
        for i in range(header.level-1, -1, -1):
            while node.forwards[i] != None and node.forwards[i].key < key:
                node = node.forwards[i]
        node = node.forwards[i]
        if node != None and node.key == key:
            node.value = value
        else:
            new_node = Node(key, value, randint(1, MAX_LEVEL))
            if new_node.level > header.level:
                header.incrLevel(new_node.level)
            for i in range(new_node.level):
                node = header
                while node.forwards[i] != None and node.forwards[i].key < key:
                    node = node.forwards[i]
                new_node.forwards[i] = node.forwards[i]
                node.forwards[i] = new_node
        return

    def search(self, key):
        header = self.header
        node = header
        for i in range(header.level-1, -1, -1):
            while node.forwards[i] != None and node.forwards[i].key < key:
                node = node.forwards[i]
        node = node.forwards[i]
        if node != None and node.key == key:
            return node.value
        else:
            return None

    def delete(self, key):
        header = self.header
        node = header
        for i in range(header.level-1, -1, -1):
            while node.forwards[i] != None and node.forwards[i].key < key:
                node = node.forwards[i]
        node = node.forwards[i]
        if node != None and node.key == key:
            delete_node = node
            for i in range(delete_node.level):
                node = header
                while node.forwards[i] != None and node.forwards[i].key < key:
                    node = node.forwards[i]
                if node.forwards[i] != None and node.forwards[i].key == delete_node.key:
                    node.forwards[i] = delete_node.forwards[i]


if __name__ == "__main__":
    num = 10000
    sl = SkipList()
    test_datas = []
    for i in range(num):
        test_datas.append(randint(0, num))

    for i in test_datas:
        sl.insert(i, i)

    for i in test_datas:
        assert(sl.search(i) == i)

    for i in test_datas:
        sl.delete(i)

    for i in test_datas:
        assert(sl.search(i) == None)
