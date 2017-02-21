# BucketAlgorithm_for_k-means-clustering_in_sliding_window
We explore K-means clustering in the sliding window model for streaming data. In the streaming model, we are restricted to use space sub-linear to the size of input and this input typically must be processed in a single pass. We have designed an algorithm called Bucket Algorithm which uses logarithmic space and time for K-means clustering in the sliding window for streaming data.This algorithm maintains two buckets each of size equal to the size of window to store active data elements from the data stream and then data in these buckets is used for clustering to get K centers using K-means clustering.



