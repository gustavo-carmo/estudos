<script>

    export default {
        splitDate(date) {
            if (date) {
                let dateSplit = date.split("/").reverse();
                return dateSplit.join("-");
            }
        },

        notifySuccess(message) {
            // console.log(message);
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

        contains(array, content) {
            var i = array.length;
            while (i--) {
                if (array[i] === content) {
                    return true;
                }
            }
            return false;
        },

        resolveCurrentDomain() {
          return document.location.origin
        }
    }

</script>
