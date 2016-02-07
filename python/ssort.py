#! /usr/bin/python
# encoding: utf8

import sys
import functools

def sentiment_sort(ratings, n=0, sort_priority=None):

    # Python 3 no longer supports a cmp function as an argument to sort
    # the functools workaround below is form the docs
    def custom_sort_function(a, b):
        x, y = abs(a), abs(b)

        if x != y or sort_priority is None:
            return x - y
        elif sort_priority > 0:
            return a - b
        else:
            return b - a

    # reverse because we want descending order
    if sort_priority is None:
        sorted_ratings = sorted(ratings, key=abs, reverse=True)
    else:
        sorted_ratings = sorted(ratings, key=functools.cmp_to_key(custom_sort_function), reverse=True)

    if n < 0:
        # this check needed because negative array indexes are valid in Python
        raise ValueError("invalid value {} for n".format(n))

    if n > 0:
        return sorted_ratings[:n]
    else:
        return sorted_ratings

#### Test Cases

import unittest

class TestSentimentSort(unittest.TestCase):

    def test_basic_cases(self):
        self.assertEqual([-5, -4, -3, -2, -1, 0], sentiment_sort([-5, -4, -3, -2, -1, 0]))
        self.assertEqual([-5, -4, -3, -2, -1], sentiment_sort([-1, -2, -3, -4, -5]))
        self.assertEqual([5, 4, 3, 2, 1, 0], sentiment_sort([5, 4, 3, 2, 1, 0]))
        self.assertEqual([5, 4, 3, 2, 1], sentiment_sort([1, 2, 3, 4, 5]))
        self.assertEqual([-4, 3], sentiment_sort([3, -4]))
        self.assertEqual([2, -1], sentiment_sort([-1, 2]))
        self.assertEqual([5, 4], sentiment_sort([4, 5]))
        self.assertEqual([-5, -4], sentiment_sort([-4, -5]))

    def test_mixed_ratings(self):
        self.assertEqual([-5, 4, -4, 3, 2, -1, 0, 0], sentiment_sort([3, 4, -1, 0, 2, -4, 0, -5]))

    def test_stable_sort(self):
        self.assertEqual([5, -5], sentiment_sort([5, -5]))
        self.assertEqual([-5, 5], sentiment_sort([-5, 5]))
        self.assertEqual([-5, -4, 3], sentiment_sort([3, -5, -4]))

    def test_first_n(self):
        ratings = [3, 4, -1, 2, -4, -5]
        self.assertEqual([-5, 4], sentiment_sort(ratings, 2))
        self.assertEqual([-5, 4, -4, 3, 2, -1], sentiment_sort(ratings, 10))
        self.assertEqual([-5, 4, -4, 3, 2, -1], sentiment_sort(ratings, 0))
        self.assertRaises(ValueError, sentiment_sort, ratings, -1)

    def test_positive_first(self):
        self.assertEqual([5, -5], sentiment_sort([5, -5], sort_priority=1))
        self.assertEqual([5, -5], sentiment_sort([-5, 5], sort_priority=1))

    def test_negative_first(self):
        self.assertEqual([-3, 3], sentiment_sort([3, -3], sort_priority=-1))
        self.assertEqual([-3, 3], sentiment_sort([-3, 3], sort_priority=-1))

if __name__=='__main__':
    unittest.TextTestRunner(verbosity=2).run(unittest.TestLoader().loadTestsFromTestCase(TestSentimentSort))
