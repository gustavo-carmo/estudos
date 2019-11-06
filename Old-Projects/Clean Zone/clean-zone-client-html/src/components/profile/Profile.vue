<template>

    <div class="cl-mcont">

        <div id="gritter-notice-wrapper">
            <div role="alert" style="" class="gritter-item-wrapper success hover" id="gritter-item-6">
                <div class="gritter-top"></div>
                <div class="gritter-item">
                    <a tabindex="1" href="" class="gritter-close">Close Notification</a>
                    <div class="gritter-without-image">
                        <span class="gritter-title">Success</span>
                        <p>This is a simple Gritter Notification.</p>
                    </div>
                    <div style="clear:both">

                    </div>
                </div>
                <div class="gritter-bottom"></div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="block-flat profile-info">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="avatar"><img src="../../../static/img/av.jpg" class="profile-avatar"></div>
                        </div>
                        <div class="col-sm-7">
                            <div class="personal">
                                <h1 class="name">{{ username }}</h1>
                                <p>{{ email }}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-sm-6">
                <div class="tab-container">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a data-toggle="tab" href="#settings">Trocar senha</a>
                        </li>
                    </ul>
                    <div class="tab-content">

                        <div class="alert alert-danger">
                            <button class="close" aria-hidden="true" data-dismiss="alert" type="button">×</button>
                            <i class="fa fa-times-circle sign"></i>
                            <strong>Error!</strong> Senha invalida
                        </div>
                        <div id="settings" class="tab-pane active">
                            <form role="form" class="form-horizontal">

                                <div class="form-group">
                                    <label for="senha-atual" class="col-sm-3 control-label">Senha atual</label>
                                    <div class="col-sm-9">
                                        <input id="inputPassword3" type="password" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword4" class="col-sm-3 control-label">Nova senha</label>
                                    <div class="col-sm-9">
                                        <input id="nova-senha" type="password" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword3" class="col-sm-3 control-label">Repita senha</label>
                                    <div class="col-sm-9">
                                        <input id="repita-senha" type="password" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button class="btn btn-success pull-right" id="not-success">
                                            Trocar senha
                                        </button>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script>

    export default {
        data() {
            return {
                username: 'Joãozinho',
                email: 'Joãozinho@hotmail.com'
            }
        }
    }
</script>
