package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

/**
 * 质量监督注册登记 实体
 * 创建人：lxx
 * 创建时间：2018/3/19 10:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualitySuperviseRegisterInfo {

    /**
     * 概况
     */
    public Brief brief;

    /**
     * 单位
     */
    public Units units;


    public static class Brief {
        /**
         * 工程地址
         */
        public String address;

        /**
         * 建设类型
         */
        public String buildingProperty;

        /**
         * 建筑高度
         */
        public String jzgd;

        /**
         * 地下层数
         */
        public String dxcs;

        /**
         * 地上总建筑面积
         */
        public String dsArea;

        /**
         * 人防面积
         */
        public String rfArea;

        /**
         * 基础类型
         */
        public String jclx;

        /**
         * 建设单位
         */
        public Jsdw jsdw;


        public static class Jsdw {
            /**
             * 名称
             */
            public String name;
        }

    }


    public static class Units {

        /**
         * 建设单位
         */
        public Jsdw jsdw;

        /**
         * 勘察单位
         */
        public Kcdw kcdw;

        /**
         * 设计单位
         */
        public Kcdw sjdw;

        /**
         * 监理单位
         */
        public Kcdw jldw;

        /**
         * 施工单位
         */
        public Sgdw sgdw;

        /**
         * 分包单位
         */
        public List<Fbdw> fbdws;

        public static class Fbdw {

            /**
             * 单位名称
             */
            public String name;

            /**
             * 资质等级
             */
            public String dwzzdj;


            /**
             * 法人代表
             */
            public Person frPerson;

            /**
             * 技术负责人
             */
            public Person jsfzrPerson;


        }

        public static class Sgdw {

            /**
             * 名称
             */
            public String name;

            /**
             * 资质等级
             */
            public String dwzzdj;

            /**
             * 企业组织机构代码
             */
            public String zzjgdmz;

            /**
             * 法人代表
             */
            public Person frPerson;

            /**
             * 建造师
             */
            public XmjlPerson xmjlPerson;


            public class XmjlPerson {
                /**
                 * 建造师等级
                 */
                public String zzdj;
            }

        }

        public static class Kcdw {
            /**
             * 名称
             */
            public String name;

            /**
             * 资质等级
             */
            public String dwzzdj;

            /**
             * 企业组织机构代码
             */
            public String zzjgdmz;

            /**
             * 法人代表
             */
            public Person frPerson;
        }

        public static class Person {
            /**
             * 身份证号
             */
            public String idCard;

            /**
             * 名字
             */
            public String name;

            /**
             * 电话
             */
            public String phone;
        }


        public static class Jsdw {
            /**
             * 地址
             */
            public String address;

            /**
             * 资质等级
             */
            public String dwzzdj;

            /**
             * 单位名称
             */
            public String name;

            /**
             * 法人代表
             */
            public Person frPerson;

            /**
             * 企业组织机构代码
             */
            public String zzjgdmz;

            /**
             * 资质证书号
             */
            public String zzzsh;

            /**
             * 项目负责人
             */
            public XmfzrPerson xmfzrPerson;


            public static class XmfzrPerson {
                /**
                 * 身份证号
                 */
                public String idCard;

                /**
                 * 名字
                 */
                public String name;

                /**
                 * 电话
                 */
                public String phone;
            }

        }


    }

}
