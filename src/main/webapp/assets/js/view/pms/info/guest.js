var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    parmaObj = $.extend({},caller.searchView.getData(),data, caller.gridView01.getPageData()),
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/pmsguest',
            data: parmaObj,
            callback: function (res) {
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        var parentData = caller.formView01.getData();
        var childList = [].concat(caller.gridView02.getData('modified'));
        childList = childList.concat(caller.gridView02.getData('deleted'));

        axboot.ajax({
            type: 'POST',
            url: '/api/v1/pmsguest',
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            },
        });
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm(
            {
                msg: LANG('ax.script.form.clearconfirm'),
            },
            function () {
                if (this.key == 'ok') {
                    caller.formView01.clear();
                    caller.gridView02.clear();
                }
            }
        );
    },
    ITEM_CLICK: function (caller, act, data) {
        caller.formView01.setData(data);
        axboot.ajax({
            type:"GET",
            url:'/api/v1/pmsguest',
            data: "parentKey=" +data.key,
            caller: function(res){
                caller.gridView02.setData(res);
            }
        });
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.gridView02.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {},
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');
        this.filter = $('#filter');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            filter: this.filter.val(),
        };
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'key', label: '이름', width: 100, align: 'left', editor: 'text' },
                { key: 'value', label: '연락처', width: 100, align: 'left', editor: 'text' },
                { key: 'etc1', label: '이메일', width: 100, align: 'center', editor: 'text' },
                { key: 'etc2', label: '성별', width: 100, align: 'center', editor: 'text' },
                { key: 'etc3', label: '생년월일', width: 100, align: 'center', editor: 'text' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                delete this.deleted;
                return this.key;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true }, 'last');
    },
});

/**
 * gridView
 */
fnObj.gridView02 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showLineNumber: false,
            showRowSelector: true,
            target: $('[data-ax5grid="grid-view-02"]'),
            columns: [
                { key: 'key', label: '투숙일', width: 80, align: 'left', editor: 'text' },
                { key: 'value', label: '숙박수', width: 70, align: 'left', editor: 'text' },
                { key: 'etc1', label: '객실번호', width: 70, align: 'center', editor: 'text' },
                { key: 'etc2', label: '객실타입', width: 70, align: 'center', editor: 'text' },
                { key: 'etc3', label: '투숙번호', width: 70, align: 'center', editor: 'text' },
            ],
            body: {
                onClick: function () {
                    //this.self.select(this.dindex);
                    //ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.list[this.dindex]);
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-02-btn', {
            'item-add': function () {
                this.addRow();
            },
            'item-remove': function () {
                this.delRow();
            },
        });
    },
    setData: function (_data) {
        this.target.setData(_data);
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                return this.key;
            });
        } else {
            list = _list;
        }
        return list;
    },
    align: function () {
        this.target.align();
    },
});
