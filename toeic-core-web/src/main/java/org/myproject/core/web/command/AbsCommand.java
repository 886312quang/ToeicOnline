package org.myproject.core.web.command;

public class AbsCommand<T> {

    protected T pojo;
    private String crudaction;


    public String getCrudaction() {
        return crudaction;
    }

    public void setCrudaction(String crudaction) {
        this.crudaction = crudaction;
    }

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

}
