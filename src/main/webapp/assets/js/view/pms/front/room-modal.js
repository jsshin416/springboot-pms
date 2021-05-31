var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_CLOSE: function (caller, act, data) {
        if (parent) {
            parent.axboot.modal.close();
        }
    },
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/pmsroom',
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

    ITEM_CLICK: function (caller, act, data) {},
    PAGE_CLOSE: function (caller, act, data) {
        var modal = fnObj.getModal();
        if (modal) modal.close();
        if (opener) window.close();
    },
    PAGE_CHOICE: function (caller, act, data) {
        if (!data) {
            var list = caller.gridView01.getData('selected');
            if (list.length > 0) data = list[0];
        }
        if (data) {
            var modal = fnObj.getModal();
            if (modal) modal.callback(data);
            if (opener) window.close();
        } else {
            alert(LANG('ax.script.requireselect'));
        }
    },
});

var CODE = {};

fnObj.getModal = function () {
    var modalView;
    if (parent && modalParams.modalView && (modalView = parent[axboot.def.pageFunctionName][modalParams.modalView])) {
        return modalView;
    } else if (opener && modalParams.modalView && (modalView = opener[axboot.def.pageFunctionName][modalParams.modalView])) {
        return modalView;
    } else if (parent && parent.axboot && parent.axboot.modal) {
        return parent.axboot.modal;
    }
};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.gridView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            choice: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CHOICE);
            },
            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            },
        });
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
                { key: 'roomNum', label: COL('room.num'), width: 100, align: 'center', editor: 'text' },
                {
                    key: 'roomTypCd',
                    label: COL('room.type'),
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['ROOM_TYPE'].map[this.value];
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['ROOM_TYPE'],
                        },
                    },
                },
                { key: 'dndYn', label: COL('room.dnd'), width: 100, align: 'center', editor: 'text' },
                { key: 'ebYn', label: COL('room.eb'), width: 100, align: 'center', editor: 'text' },
                {
                    key: 'roomSttusCd',
                    label: COL('room.rooms'),
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['ROOM_STATUS'].map[this.value];
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['ROOM_STATUS'],
                        },
                    },
                },
                {
                    key: 'clnSttusCd',
                    label: COL('room.clns'),
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['CLEAN_STATUS'].map[this.value];
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['CLEAN_STATUS'],
                        },
                    },
                },
                {
                    key: 'svcSttusCd',
                    label: COL('room.svcs'),
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['SVC_STATUS'].map[this.value];
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'code',
                                optionText: 'name',
                            },
                            options: parent.COMMON_CODE['SVC_STATUS'],
                        },
                    },
                },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex);
                    ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
                },
                onDBLClick: function () {
                    ACTIONS.dispatch(ACTIONS.PAGE_CHOICE, this.item);
                },
            },
        });
    },
});
