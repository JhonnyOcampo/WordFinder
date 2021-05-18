# WordFinder

Program that finds all of the English words in a given string. For example, if the string **working** is given, it will return the words **work**, **king**, **row**, **ring**, **know**, among others.

To solve this problem, it generates all possible subsets of the given string, permutes each generated subset, and validates whether each generated permutation is an English word. To improve the performance of this approach, it only generates subsets that contain a vowel (a, e, i, o, u, y) because an English word must have at least one vowel. The step-by-step solution of this problem is described below.

1. Lowercase all letters of the given string and build a new string with only the characters between 'a' and 'z'.
2. Create a bitmask that indicates which characters in the string are vowels. The least significant bit indicates whether the first character (position 0) in the string is a vowel. If the bit is on then it is a vowel, otherwise it is not. If the bitmask is 0 then an empty collection is returned. 
3. Generate all possible subsets of the string. These subsets can be generated using numbers from 0 to 2^n where n is the length of the string, through binary operations, each number can tell us what characters we must take to form each combination. Additionally, it must be validated if each combination contains at least one vowel, this is checked by applying the bitwise AND operation between the bitmask calculated in point 2 and the number of the combination.
4. Generate all possible permutations of the subsets created in the previous point. This is achieved by implementing the non-recursive version of the Heap's algorithm.
5. Each permutation generated from the previous point is validated against a dictionary, if the validation is fulfilled then the word is stored in a HashSet to avoid duplicates. 
6. The HashSet is returned with all found English words.
