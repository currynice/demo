package com.cxy.demo.demoasync.jdk8CompletableFuture;


import com.cxy.demo.demoasync.jdk5Future.AsyncMethod;
import com.cxy.demo.demoasync.service.TestException;
import com.cxy.demo.demoasync.util.DateUtil;

import java.text.ParseException;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *CompletableFuture has implemented 2 interfaces:
 * <1> Future   <2> CompletionStage
 *
 *
 * Future isDone() 任务是否完成 , get()获得返回的结果都是线程池替我们做的，success/fail没有通知
 * 而且并不知道什么时候完成,需要等待或轮询
 * {@link CompletableFuture#complete(Object)} help us  Future、Promise 和 Callback
 */
public class CompletableFurtureTest {

    public static void completeTest() throws ExecutionException, InterruptedException {
//        ExecutorService fixedService = Executors.newFixedThreadPool(4);
//        CompletableFuture<String>  completableFuture  = fixedService.submit(()->{return 10;});
        CompletableFuture<String>  completableFuture = new CompletableFuture<>();
        //get()是阻塞操作,可以
        if(completableFuture.complete("default"))
        System.out.println(completableFuture.get());
    }

    /**
     * {@link CompletableFuture#runAsync(Runnable)}
     * 未指定 Exeactor ,{@link ForkJoinPool#commonPool()} default
     * no return value    没返回值
     */
    public static void testRunAsync(){
        ExecutorService fixedService = Executors.newFixedThreadPool(4);
        CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println("并行运行异步任务");
        },fixedService);
    }

    /**
     * return a value which type is String  有返回值
     * 未指定 Exeactor ,{@link ForkJoinPool#commonPool()} default
     */
    public static void testSupplyAsync(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            return "cxy";
        });
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //map转换 Integer -> String

    public static void testThenApply(){
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            return 7;
        });

        CompletableFuture<String> completableFuture2 = completableFuture.thenApply(num -> {
            return "NO"+num;
        });
        try {
            System.out.println(completableFuture.get());
            System.out.println(completableFuture2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 纯消费:只会对计算结果进行消费而不会返回任何结果的方法
     *
     */
    public static void testThenAccept(){
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            return "cxy";
        }).thenAccept(name->{
            System.out.println(name);
        });
    }

    public static void testThenRun(){
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            return "cxy";
        }).thenRun(()->{
            System.out.println("thenRun()");
        });
    }

    //combine  合并两个CompletableFuture,创建异步工作流,这是Future无法完成的

    /**
     *  在异步操作完成的时候对异步操作的结果进行一些操作，并且仍然返回CompletableFuture类型。(未使用线程池)
     *  将前一个结果作为下一个计算的参数，它们之间存在着先后顺序。
     *  {@link CompletableFuture#thenComposeAsync} 可以指定线程池
     */
    public static void testThenCompose(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            return "cxy";
        });


        CompletableFuture<String> completableFuture2 = completableFuture.thenCompose(name ->{
            return CompletableFuture.supplyAsync(()->{
                return "hi";
        });
    });
        try {
            System.out.println(completableFuture2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



    //combine  CompletableFuture<T>  和CompletableFuture<U> ,通过Function (T,U)->V, 将两个CompletableFuture组合成 CompletableFuture<V>

    /**
     * 同样{@link CompletableFuture#thenCombineAsync} 可以指定线程池
     * 将两个结果并行运行，之后再组合。
     */
    public static void testCombine(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
           return "100";
        }) ;
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(()->{
            return 100.00;
        }) ;

        CompletableFuture<Double> result = future1.thenCombine(future2,(s,d)->{
           return Double.parseDouble(s+d);
        });

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    //completable

    /**
     * future调用complete(T t)会立即执行。但是complete(T t)只能调用一次，重复调用无效。
     *
     * 如果future已经执行完毕能够返回结果，此时再调用complete(T t)则会无效
     */
    public static void testComolete(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            return "result";
        });

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        completableFuture.complete("无法执行");
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 未完成的情况下,抛出异常
     */
    public static void testComoleteExceptionally(){
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
            return "result";
        });
        completableFuture.completeExceptionally(new TestException("error in testComoleteExceptionally"));
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
   public static void  whenCompleteTest() {
       CompletableFuture.supplyAsync(() -> "hello world")
               .thenApply(s -> {
                   s = null;
                   int length = s.length();
                   return length;
               }).thenAccept(i -> System.out.println(i))
               .whenComplete((result, throwable) -> {

                   if (throwable != null) {
                       System.out.println("Unexpected error:" + throwable);
                   } else {
                       System.out.println(result);
                   }

               });
   }



    //handler

    /**
     * 如果发生异常，则result将为null，否则ex参数将为null
     */
    public static void handlerTest()  {
        CompletableFuture a = CompletableFuture.supplyAsync(()->{
            return "2019/09/09";
        }).thenApply(s -> {
            try {
               return DateUtil.str2Date(s);
            } catch (ParseException e) {
              return e;
            }
        }
        ).handle((result, ex) -> {
            if (ex != null) {
                return ex.getMessage();
            } else {
                return result;
            }
        });
        try {
            System.out.println(a.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    //exceptionally

    /**
     * 之前的函数执行失败，执行替代函数
     */
    public static void exceptionally(){
        String a = CompletableFuture.supplyAsync(()->Integer.parseInt("22")).
                thenApply(s -> "s:" + s).exceptionally(ex -> ex.getMessage()).join();
        System.out.println(a);//s:22

        String a2 = CompletableFuture.supplyAsync(()->Integer.parseInt("sss")).
                thenApply(s -> "s:" + s).exceptionally(ex -> ex.getMessage()).join();
        System.out.println(a2);//java.lang.NumberFormatException: For input string: "sss"
    }

    //either两个(only 2)CompletableFuture，当其中任意一个CompletableFuture计算完成的时候就会执行
  public static void testEither(){
      Random random = new Random();

      CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
          try {
              Thread.sleep(random.nextInt(1000));
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          return "from future1";
      });

      CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{

          try {
              Thread.sleep(random.nextInt(1000));
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          return "from future2";
      });

      CompletableFuture<Void> future =  future1.acceptEither(future2,str->System.out.println("The future is "+str));

      try {
          future.get();
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }

  }

    /**
     * 多个
     */
    public static void testAnyOf(){
        Random random = new Random();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future3";
        });

        CompletableFuture future =  CompletableFuture.anyOf(future1,future2,future3).thenApply(str->{
            return "The future is "+str;
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }







    public static void main(String args[]){

        //testRunAsync();
//        testSupplyAsync();
//        testThenApply();
//        testThenAccept();
//        testThenRun();
//        testThenCompose();
//        testComolete();
//        testComoleteExceptionally();
//        testCombine();
//        handlerTest();
//        testEither();
//        testAnyOf();
//        exceptionally();
        whenCompleteTest();
    }

}
