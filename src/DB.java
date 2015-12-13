class DB
{
    public DB(String dbfile)
    {

    }
    public DB()
    {
        this("db.sqlite3");
    }
    public boolean add(String name)
    {
        return true;
    }
    public void delete(String name)
    {

    }
    public void edit(String name,String code)
    {

    }
    public String getCode(String name)
    {
        return "int main";
    }
    public String[] getList()
    {
        return new String[]{"0-1backpack","SPFA","Dijkstra","ISAP","Dinic","MaxMatch"};
    }
}