# INFO6205-project-sorting-Chinese
#### To benchmark sorting algorithms, run the main function in [`SortingBenchmark.java`](/main/src/main/java/edu/neu/coe/info6205/SortingBenchmark.java)
- Comment out the sorting algorithms [here](/main/java/edu/neu/coe/info6205/SortingBenchmark.java#L38) if you don't want to run it
- Modify the `BENCHMARK_RUNS` variable's value to change the number of runs
- Experiment results are stored in [`experiment_result.xlsx`](/main/experiment_result.xlsx)

### Sorted chinese names are stored in [output folder](/src/main/resources/sortedChinese.txt)

#### 6 different sorting methods are included in this repo
- [LSD radix sort](/src/main/java/edu/neu/coe/info6205/indexSort/LSDRadixSort.java)
- MSD radix sort
  - [MSD radix sort with 8 bits bucket](/src/main/java/edu/neu/coe/info6205/indexSort/MSDRadixSort.java)
  - [MSD radix sort with 16 bits bucket](/src/main/java/edu/neu/coe/info6205/indexSort/MSDRadixSort16Bits.java)
  - [MSD radix sort with cache improvement (8 bits)](/src/main/java/edu/neu/coe/info6205/indexSort/MSDRadixSortCacheImproved.java)
- [Quicksort](/src/main/java/edu/neu/coe/info6205/compSort/QuickSort.java)
- [Dual-pivot quicksort](/src/main/java/edu/neu/coe/info6205/compSort/DualPivotSort.java)
- [Timsort](/src/main/java/edu/neu/coe/info6205/compSort/TimSort.java)
- [HuskySort](/src/main/java/edu/neu/coe/info6205/compSort/huskySort/sort/PureHuskySort.java)
  - Code copied from [HuskySort original repo](https://github.com/rchillyard/The-repository-formerly-known-as) with slight modification
  - Paper: [https://arxiv.org/abs/2012.00866](https://arxiv.org/abs/2012.00866) by R.C. Hillyard

##### Benchmark class copied from [INFO6205 repo](https://github.com/NiftyMule/INFO6205/blob/Fall2021/src/main/java/edu/neu/coe/info6205/util/) for assignment 2

##### Unit tests are located under [test folder](/main/src/test/java/edu/neu/coe/info6205)

