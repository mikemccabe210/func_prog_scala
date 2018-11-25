import calculator._

val a = Var("taco")

val b = Var(a() + 1)


b()

a() = "wildebeast"

b()

TweetLength.tweetRemainingCharsCount(b)()

a() = "zebra"

TweetLength.colorForRemainingCharsCount(TweetLength.tweetRemainingCharsCount(b))()



a() = "fnhjdsbflkabndfhladdfdfddsfklkjfjsdhjfidsifjdsilufslhbfjbsdfbdsbfbdsjkfbjkdsblfhkdsbkljfndsjnfkjsdnjklfbjfhlbsldhbfljadsblfhbdsjlbfjlds"

TweetLength.colorForRemainingCharsCount(TweetLength.tweetRemainingCharsCount(b))()

Set("a","b")