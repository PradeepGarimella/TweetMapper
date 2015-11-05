//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.ServerConnector;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//import twitter.TweetBroker;
//import twitter4j.*;
//
//
//public class AppDriver {
//
//    public static void main(String[] args) throws Exception {
//        new Thread(() -> {
//            TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
//            StatusListener statusListener = TweetBroker.getInstance();
//            twitterStream.addListener(statusListener);
//            FilterQuery x =  new FilterQuery();
//            double[][] loc = new double[2][2];
//            loc[0][0] = -124.06;
//            loc[0][1] = 47.064;
//            loc[1][0] = -120.19;
//            loc[1][1] = 48.36;
////            x.track("usa");
//            x.locations(loc);
//            twitterStream.filter(x);
////            twitterStream.sample();
//            System.out.printf("Done");
//        }).start();
//
//        Server server = new Server();
//        ServerConnector connector = new ServerConnector(server);
//        connector.setPort(7070);
//        server.addConnector(connector);
//
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//        server.setHandler(context);
//
//        ServletHolder holderEvents = new ServletHolder("ws-events", EventServlet.class);
//        context.addServlet(holderEvents, "/events/*");
//
//        try
//        {
//            server.start();
////            server.dump(System.err);
////            server.join();
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace(System.err);
//        }
//
//
//
//
//
//    }
//
//
//}
//
//
