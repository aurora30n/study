package study.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZkUtil {


    private static final Logger logger = LoggerFactory.getLogger(ZkUtil.class);
    private static ZkUtil intance;
    private Map<String, CuratorFramework> clients = new ConcurrentHashMap();
    private Object o = new Object();

    private ZkUtil() {
    }

    public String getZkConnectString() {
        String zk = PropUtil.getValueByKey("zk.properties", "zk.connectString");
        return zk;
    }

    public static ZkUtil getInstance() {
        if (intance == null) {
            Class var0 = ZkUtil.class;
            synchronized(ZkUtil.class) {
                if (intance == null) {
                    intance = new ZkUtil();
                }
            }
        }

        return intance;
    }

    void connect(String connectString) {
        synchronized(this.o) {
            CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(1000, 3));
            client.start();
            CuratorFramework o = (CuratorFramework)this.clients.putIfAbsent(connectString, client);
            if (o != null) {
                client.close();
            }

        }
    }

    public CuratorFramework getConnection() {
        return this.getConnection(this.getZkConnectString());
    }

    public CuratorFramework getConnection(String connectString) {
        if (!this.clients.containsKey(connectString)) {
            this.connect(connectString);
        }

        return (CuratorFramework)this.clients.get(connectString);
    }

    public Stat create(String path, String value, CreateMode mode) throws Exception {
        Stat stat = (Stat)this.getConnection().checkExists().forPath(path);
        if (stat != null) {
            return stat;
        } else {
            ((ACLBackgroundPathAndBytesable)this.getConnection().create().creatingParentsIfNeeded().withMode(mode)).forPath(path, value.getBytes());
            return (Stat)this.getConnection().checkExists().forPath(path);
        }
    }

    public byte[] getData(String path) throws Exception {
        Stat stat = (Stat)this.getConnection().checkExists().forPath(path);
        return stat == null ? null : (byte[])this.getConnection().getData().forPath(path);
    }

    public Stat update(String path, String value) throws Exception {
        Stat stat = (Stat)this.getConnection().checkExists().forPath(path);
        return stat == null ? null : (Stat)this.getConnection().setData().forPath(path, value.getBytes());
    }

    public void delete(String nodeName) throws Exception {
        this.getConnection().delete().deletingChildrenIfNeeded().forPath(nodeName);
    }

    public List<String> listChildren(String node) throws Exception {
        GetChildrenBuilder childrenBuilder = this.getConnection().getChildren();
        return (List)childrenBuilder.forPath(node);
    }

    public Stat create(CuratorFramework curator, String path, String value, CreateMode mode) throws Exception {
        Stat stat = (Stat)curator.checkExists().forPath(path);
        if (stat != null) {
            return stat;
        } else {
            ((ACLBackgroundPathAndBytesable)curator.create().creatingParentsIfNeeded().withMode(mode)).forPath(path, value.getBytes());
            return (Stat)curator.checkExists().forPath(path);
        }
    }

    public byte[] getData(CuratorFramework curator, String path) throws Exception {
        Stat stat = (Stat)curator.checkExists().forPath(path);
        return stat == null ? null : (byte[])curator.getData().forPath(path);
    }

    public Stat update(CuratorFramework curator, String path, String value) throws Exception {
        Stat stat = (Stat)curator.checkExists().forPath(path);
        return stat == null ? null : (Stat)curator.setData().forPath(path, value.getBytes());
    }

    public void delete(CuratorFramework curator, String nodeName) throws Exception {
        curator.delete().deletingChildrenIfNeeded().forPath(nodeName);
    }

    public List<String> listChildren(CuratorFramework curator, String node) throws Exception {
        GetChildrenBuilder childrenBuilder = curator.getChildren();
        return (List)childrenBuilder.forPath(node);
    }

    public void addWatch(String path, NodeCacheListener watcher) throws Exception {
        this.addWatch(this.getConnection(this.getZkConnectString()), path, watcher);
    }

    public NodeCache addWatch(CuratorFramework curator, String path, NodeCacheListener watcher) throws Exception {
        NodeCache nodeCache = new NodeCache(curator, path);
        nodeCache.getListenable().addListener(watcher);
        nodeCache.start();
        return nodeCache;
    }

    public void addWatchChildren(String path, TreeCacheListener watcher) throws Exception {
        this.addWatchChildren(this.getConnection(this.getZkConnectString()), path, watcher);
    }

    public TreeCache addWatchChildren(CuratorFramework curator, String path, TreeCacheListener watcher) throws Exception {
        TreeCache treeCache = new TreeCache(curator, path);
        treeCache.getListenable().addListener(watcher);
        treeCache.start();
        return treeCache;
    }

    public void close() {
        String bootstrap = this.getZkConnectString();
        if (bootstrap == null) {
            logger.error("Please configure zk.connectString.");
        }

        this.close(bootstrap);
    }

    public void close(String bootstrap) {
        CuratorFramework curator = (CuratorFramework)this.clients.remove(bootstrap);
        if (curator != null) {
            curator.close();
        }

    }

    public static void main(String[] args) throws Exception {
        Stat stat = null;
        byte[] data = null;

        stat = ZkUtil.getInstance().create("/data", "", CreateMode.PERSISTENT);
        System.out.println(String.format("create:%s", stat!=null));

        data = ZkUtil.getInstance().getData("/data");
        System.out.println(String.format("getData:%s", new String(data)));

        stat = ZkUtil.getInstance().update("/data", "test");
        System.out.println(String.format("update:%s", stat!=null));

        ZkUtil.getInstance().delete("/data");

        ZkUtil.getInstance().close();
    }

}
