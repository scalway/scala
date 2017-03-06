package scala.collection.immutable

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.infra._
import org.openjdk.jmh.annotations._
import benchmark._


@BenchmarkMode(Array(Mode.AverageTime))
@Fork(2)
@Threads(1)
@Warmup(iterations = 15)
@Measurement(iterations = 15)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
class IterableLikeBenchmark {
  class Obj(val id:Int)

  @Param(Array("0", "1", "10", "100", "1000"))
  var size: Int = _

  var seq: Seq[Obj] = _
  var vec: Vector[Obj] = _
  var list: List[Obj] = _
  var stack: Stack[Obj] = _
  var range: Range = _

  @Setup(Level.Trial) def initKeys(): Unit = {
    seq = Seq.tabulate(size)(v => new Obj(v))
    vec = Vector.tabulate(size)(v => new Obj(v))
    list = List.tabulate(size)(v => new Obj(v))
    stack = Stack.tabulate(size)(v => new Obj(v))
    range = Range(0,size)
  }

  @Benchmark def zipWithIndex_seq(bh: Blackhole): Any = seq.zipWithIndex.foreach(r => bh.consume(r))
  @Benchmark def zipWithIndex_vec(bh: Blackhole): Any = vec.zipWithIndex.foreach(r => bh.consume(r))
  @Benchmark def zipWithIndex_list(bh: Blackhole): Any = list.zipWithIndex.foreach(r => bh.consume(r))
  @Benchmark def zipWithIndex_stack(bh: Blackhole): Any = stack.zipWithIndex.foreach(r => bh.consume(r))
  @Benchmark def zipWithIndex_range(bh: Blackhole): Any = range.zipWithIndex.foreach(r => bh.consume(r))

//    jmh:run ".*IterableLike.*"
//
//    [info] Benchmark WHILE                           (size)  Mode  Cnt      Score      Error  Units
//    [info] IterableLikeBenchmark.zipWithIndex_list        0  avgt   30     11,565 ±    1,157  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list        1  avgt   30     22,604 ±    0,339  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list       10  avgt   30    137,144 ±    1,491  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list      100  avgt   30   1345,444 ±   87,968  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list     1000  avgt   30  16859,651 ±  145,919  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range       0  avgt   30     40,151 ±    5,272  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range       1  avgt   30     62,786 ±   12,281  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range      10  avgt   30    122,546 ±    1,193  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range     100  avgt   30   1032,153 ±   55,075  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range    1000  avgt   30  16147,710 ± 1747,483  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq         0  avgt   30     13,931 ±    2,419  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq         1  avgt   30     23,959 ±    1,318  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq        10  avgt   30    139,167 ±    7,625  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq       100  avgt   30   1291,813 ±   10,118  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq      1000  avgt   30  17036,874 ±  143,617  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack       0  avgt   30    105,911 ±    1,112  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack       1  avgt   30    134,743 ±    1,387  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack      10  avgt   30    445,860 ±    8,296  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack     100  avgt   30   3615,112 ±   52,887  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack    1000  avgt   30  46033,266 ± 2491,527  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec         0  avgt   30     37,377 ±    0,251  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec         1  avgt   30     76,213 ±    0,912  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec        10  avgt   30    123,971 ±    0,830  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec       100  avgt   30    968,860 ±    5,304  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec      1000  avgt   30  11339,045 ±  756,775  ns/op

//    [info] Benchmark FOR                             (size)  Mode  Cnt      Score     Error  Units
//    [info] IterableLikeBenchmark.zipWithIndex_list        0  avgt   30     12,051 ±   0,093  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list        1  avgt   30     28,880 ±   0,179  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list       10  avgt   30    197,315 ±   1,280  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list      100  avgt   30   1829,966 ±  11,076  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_list     1000  avgt   30  23133,954 ± 294,997  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range       0  avgt   30     29,906 ±   0,273  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range       1  avgt   30     64,140 ±   0,338  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range      10  avgt   30    138,450 ±   1,991  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range     100  avgt   30   1069,267 ±  14,889  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_range    1000  avgt   30  12832,226 ± 158,208  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq         0  avgt   30     11,419 ±   0,080  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq         1  avgt   30     28,260 ±   0,204  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq        10  avgt   30    193,188 ±   2,063  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq       100  avgt   30   2663,089 ±  52,966  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_seq      1000  avgt   30  28644,615 ± 599,120  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack       0  avgt   30    111,985 ±   3,005  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack       1  avgt   30    146,530 ±   6,292  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack      10  avgt   30    368,407 ±  42,142  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack     100  avgt   30   2630,878 ±  89,120  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_stack    1000  avgt   30  27427,866 ± 932,678  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec         0  avgt   30     56,565 ±   1,502  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec         1  avgt   30     80,853 ±   2,718  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec        10  avgt   30    249,086 ±   4,419  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec       100  avgt   30   1843,284 ± 250,352  ns/op
//    [info] IterableLikeBenchmark.zipWithIndex_vec      1000  avgt   30  17729,429 ± 472,765  ns/op


}
