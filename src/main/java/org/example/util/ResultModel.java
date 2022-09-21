package org.example.util;

/**
 * 因为layui的table模块返回的数据接口的格式固定了
 */
public class ResultModel {

    // 是否成功 true成功  false失败
    private Boolean success;

    // 状态码：成功0，失败1
    private Integer code;

    //返回消息
    private String msg;

    // 返回数据复合数据，如用户对象user，集合list对象 list 对象 数组
    //	 * 表格填充数据
    private Object data;

    // 数据的条数,可以用于分页展示
    private Integer count;

    //设置跳转的页面
    private String location;

    public ResultModel() {

    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    //-----------------为了方便调用，创建一些方法，其他类中可以直接调用，不用自己new对象---------------------------------

    /**
     成功调用的方法
     * @param rightMsg
     * @param data
     * @return
     */
    public static ResultModel success(String rightMsg,Object data){
        ResultModel resultModel=new ResultModel();
        //是否成功 true成功  false失败
        resultModel.setSuccess(true);
        //状态码: 0 成功   1失败
        resultModel.setCode(0);
        //设置正确的信息
        resultModel.setMsg(rightMsg);
        //设置传递给Servlet的值
        resultModel.setData(data);
        return resultModel;
    }
    public static ResultModel success(String rightMsg){
        ResultModel resultModel=new ResultModel();
        //是否成功 true成功  false失败
        resultModel.setSuccess(true);
        //状态码: 0 成功   1失败
        resultModel.setCode(0);
        //设置正确的信息
        resultModel.setMsg(rightMsg);
        //说明没有参数值传递给Servlet
        return resultModel;
    }

    //layui请求，返回table组件所需要的数据
    public static ResultModel success(long count, Object data) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);// 0成功
        resultModel.setData(data);// 显示的数据
        resultModel.setCount((int)count);// 总记录数
        return resultModel;
    }

    public static ResultModel fail(String errMsg){
        ResultModel resultModel=new ResultModel();
        //是否成功 true成功  false失败
        resultModel.setSuccess(false);
        //状态码: 0 成功   1失败
        resultModel.setCode(1);
        //设置错误的信息
        resultModel.setMsg(errMsg);
        return resultModel;
    }


    @Override
    public String toString() {
        return "ResultModel{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", location='" + location + '\'' +
                '}';
    }
}

