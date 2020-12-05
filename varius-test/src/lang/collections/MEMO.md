# Collection
## 継承関係
### 配列
Array

### Collection
 - List
   - ArrayList
     - field
       elementData: 配列情報保持(空の配列もある)
       size: 配列に格納している数を保持

     - add
        毎回配列を作り直して、最後のポインターに格納
        配列のサイズは10から始まって、1.5倍ずつ増えていく。
        複数の配列を同時にセットする場合は、1.5倍と比較して大きい方を採用
        単純に末尾にaddするならArrayListの方が速い

     - get(indexOf, contains)
     
     -set

     - remove

   - LinkedList
 - Set
   - HashSet
   - TreeSet
   - LinkedHashSet
 - Queue
   - PriorityQueue
   - Deque
     - ArrayDeque

immutable List も

## Map
 - HashMap
 - LinkedHashMap
 - TreeMap

