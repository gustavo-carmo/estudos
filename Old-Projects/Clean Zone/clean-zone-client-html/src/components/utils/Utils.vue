<script>

    export default {
        splitDate(date) {
            if (date) {
                let dateSplit = date.split("/").reverse();
                return dateSplit.join("-");
            }
        },

        notifySuccess(message) {
            console.log(message);
            $.gritter.add({
                title: 'Sucesso',
                text: message,
                class_name: 'success'
            });
        },
        notifySuccessMessage(id, entity) {

            let message = entity;
            if (id) {
                message += ' atualizado ';
            } else {
                message += ' criado';
            }

            $.gritter.add({
                title: 'Sucesso',
                text: message,
                class_name: 'success'
            });
        },

        buildErrorMessage(errorsParam) {

            let errors = [];

            let i;
            for (i in errorsParam) {
                let error = errorsParam[i];
                errors[error.field] = error;
            }

            return errors
        },

        contains(array, content) {
            var i = array.length;
            while (i--) {
                if (array[i] === content) {
                    return true;
                }
            }
            return false;
        }
    }

</script>
