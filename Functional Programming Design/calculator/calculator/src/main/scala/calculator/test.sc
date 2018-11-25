import calculator._

val a = Var("taco")

val b = Var(a() + 1)


b()

a() = "wildebeast"

b()

TweetLength.tweetRemainingCharsCount(b)()

a() = "zebra"

val c =TweetLength.colorForRemainingCharsCount(TweetLength.tweetRemainingCharsCount(b))

c()

a() = "fnhjdsbflkabnfddfvddfhladdfdfddsfklkjfjsdhjfidsifjdsilufslhbfjbsdfbdsbfbdsjkfbjkdsblfhkdsbkljfndsjnfkjsdnjklfbjfhlbsldhbfljadsblfhbdsjlbfjlds"

TweetLength.colorForRemainingCharsCount(TweetLength.tweetRemainingCharsCount(b))()

c()