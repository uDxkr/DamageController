# DamageManager

Plugin para Spigot/Bukkit **1.8.9** que permite ajustar o multiplicador de dano de espada/machado de diamante no servidor.

## Comandos

| Comando | Descrição                                                  |
|---|------------------------------------------------------------|
| `/damage <valor>` | Define o multiplicador de dano, podes aumentar ou diminuir |
| `/damage info` | Mostra o multiplicador de dano atual.                      |

**Exemplos:**
```
/damage 1.5       -> Dano configurado para: 1.5
/damage 0.5       -> Dano configurado para: 0.5 (dano reduzido para metade)
/damage info       -> Multiplicador de dano atual: 1.5
```

O valor tem de estar entre **0.01** e **40.0**.

## Permissões

| Permissão | Descrição                                                          | Padrão |
|---|--------------------------------------------------------------------|---|
| `damagecontroller.value` | Essa e a permissao para o staff conseguir dar o comando `/damage`. | `op` |

## Configuração

Ao arrancar pela primeira vez, o plugin cria automaticamente o ficheiro `damage.yml` dentro de `plugins/DamageManager/`, com o seguinte conteúdo por omissão:

```yaml
Damage:
  Multiplier: 1.0
```

Este valor é atualizado sempre que o comando `/damage <valor>` é usado, o multiplicador so e aplicado em espadas e maxados de diamante.

## Instalação

1. Compila o projeto com `./gradlew clean shadowJar`.
2. Copia o jar gerado (`build/libs/DamageManager-1.0.0.jar`) para a pasta `plugins/` do teu servidor.
3. Reinicia ou recarrega da load no plugin.

## Requisitos

- Servidor Spigot/Bukkit **1.8.9**
- Java 8+